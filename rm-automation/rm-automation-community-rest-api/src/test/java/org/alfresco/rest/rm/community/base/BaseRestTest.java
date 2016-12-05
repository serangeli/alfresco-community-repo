/*
 * #%L
 * Alfresco Records Management Module
 * %%
 * Copyright (C) 2005 - 2016 Alfresco Software Limited
 * %%
 * This file is part of the Alfresco software.
 * -
 * If the software was purchased under a paid Alfresco license, the terms of
 * the paid license agreement will prevail.  Otherwise, the software is
 * provided under the following open source license terms:
 * -
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * -
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * -
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package org.alfresco.rest.rm.community.base;

import static java.lang.Integer.parseInt;

import static org.alfresco.rest.rm.community.base.TestData.CATEGORY_TITLE;
import static org.alfresco.rest.rm.community.base.TestData.FOLDER_TITLE;
import static org.alfresco.rest.rm.community.model.fileplancomponents.FilePlanComponentType.RECORD_CATEGORY_TYPE;
import static org.alfresco.rest.rm.community.model.fileplancomponents.FilePlanComponentType.RECORD_FOLDER_TYPE;
import static org.alfresco.rest.rm.community.model.fileplancomponents.FilePlanComponentType.UNFILED_RECORD_FOLDER_TYPE;
import static org.alfresco.rest.rm.community.model.site.RMSiteCompliance.STANDARD;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.jayway.restassured.RestAssured;

import org.alfresco.rest.RestTest;
import org.alfresco.rest.core.RestWrapper;
import org.alfresco.rest.rm.community.model.fileplancomponents.FilePlanComponent;
import org.alfresco.rest.rm.community.model.fileplancomponents.FilePlanComponentProperties;
import org.alfresco.rest.rm.community.model.fileplancomponents.FilePlanComponentType;
import org.alfresco.rest.rm.community.model.site.RMSite;
import org.alfresco.rest.rm.community.requests.FilePlanComponentAPI;
import org.alfresco.rest.rm.community.requests.RMSiteAPI;
import org.alfresco.utility.constants.UserRole;
import org.alfresco.utility.data.DataUser;
import org.alfresco.utility.model.SiteModel;
import org.alfresco.utility.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.testng.annotations.BeforeClass;

/**
 * Base class for all IG REST API Tests
 *
 * @author Kristijan Conkas
 * @author Tuna Aksoy
 * @since 2.6
 */
@Configuration
@PropertySource(value = {"classpath:default.properties", "classpath:config.properties"})
@PropertySource(value = "classpath:module.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:local.properties", ignoreResourceNotFound = true)
public class BaseRestTest extends RestTest
{
    @Value ("${alfresco.scheme}")
    private String scheme;

    @Value ("${alfresco.server}")
    private String server;

    @Value ("${alfresco.port}")
    private String port;

    @Value ("${rest.rmPath}")
    private String restRmPath;

    @Value ("${rest.basePath}")
    private String restCorePath;

    @Autowired
    private RMSiteAPI rmSiteAPI;
    
    @Autowired
    private DataUser dataUser;

    @Autowired
    public FilePlanComponentAPI filePlanComponentAPI;

    // Constants
    public static final String RM_ID = "rm";
    public static final String RM_TITLE = "Records Management";
    public static final String RM_DESCRIPTION = "Records Management Site";

    /**
     * @see org.alfresco.rest.RestTest#checkServerHealth()
     */
    @Override
    @BeforeClass (alwaysRun = true)
    public void checkServerHealth() throws Exception
    {
        RestAssured.baseURI = scheme + "://" + server;
        RestAssured.port = parseInt(port);
        RestAssured.basePath = restRmPath;

        // Create RM Site if not exist
        createRMSiteIfNotExists();
    }

    /**
     * Helper method to create the RM Site via the POST request
     * if the site doesn't exist
     */
    public void createRMSiteIfNotExists() throws Exception
    {
        // Check RM site doesn't exist
        if (!rmSiteAPI.existsRMSite())
        {
            rmSiteAPI.usingRestWrapper().authenticateUser(dataUser.getAdminUser());

            // Create the RM site
            RMSite rmSite = new RMSite(RM_TITLE, RM_DESCRIPTION, STANDARD);
            rmSiteAPI.createRMSite(rmSite);

            // Verify the status code
            rmSiteAPI.usingRestWrapper().assertStatusCodeIs(CREATED);
        }
    }

    /**
     * Helper method to create child category
     *
     * @param parentCategoryId The id of the parent category
     * @param categoryName     The name of the category
     * @return The created category
     * @throws Exception on unsuccessful component creation
     */
    public FilePlanComponent createCategory(String parentCategoryId, String categoryName) throws Exception
    {
        return createComponent(parentCategoryId, categoryName, RECORD_CATEGORY_TYPE, CATEGORY_TITLE);
    }

    /**
     * Helper method to create child folder
     *
     * @param parentCategoryId The id of the parent category
     * @param folderName       The name of the category
     * @return The created category
     * @throws Exception on unsuccessful component creation
     */
    public FilePlanComponent createFolder(String parentCategoryId, String folderName) throws Exception
    {
        return createComponent(parentCategoryId, folderName, RECORD_FOLDER_TYPE, FOLDER_TITLE);
    }

    /**
     * Helper method to create child unfiled record folder
     *
     * @param parentId The id of the parent folder
     * @param folderName       The name of the folder
     * @return The created folder
     * @throws Exception on unsuccessful component creation
     */
    public FilePlanComponent createUnfiledRecordsFolder(String parentId, String folderName) throws Exception
    {
        return createComponent(parentId, folderName, UNFILED_RECORD_FOLDER_TYPE, FOLDER_TITLE);
    }

    /**
     * Helper method to create generic child component
     *
     * @param parentComponentId The id of the parent file plan component
     * @param componentName     The name of the file plan component
     * @param componentType     The name of the file plan component
     * @param componentTitle
     * @return The created file plan component
     * @throws Exception
     */
    private FilePlanComponent createComponent(String parentComponentId, String componentName, FilePlanComponentType componentType, String componentTitle) throws Exception
    {
        RestWrapper restWrapper = filePlanComponentAPI.usingRestWrapper().authenticateUser(dataUser.getAdminUser());

        FilePlanComponent filePlanComponent = new FilePlanComponent(componentName, componentType.toString(),new FilePlanComponentProperties(componentTitle));

        FilePlanComponent fpc = filePlanComponentAPI.createFilePlanComponent(filePlanComponent, parentComponentId);
        restWrapper.assertStatusCodeIs(CREATED);
        return fpc;
    }

    /**
     * Helper method to close folder
     * @param folderToClose
     * @return
     * @throws Exception
     */
    public FilePlanComponent closeFolder(String folderId) throws Exception
    {
        RestWrapper restWrapper = filePlanComponentAPI.usingRestWrapper().authenticateUser(dataUser.getAdminUser());
        
        // build fileplan component + properties for update request
        FilePlanComponentProperties properties = new FilePlanComponentProperties();
        properties.setIsClosed(true);
        FilePlanComponent filePlanComponent = new FilePlanComponent();
        filePlanComponent.setProperties(properties);
        
        FilePlanComponent updatedComponent = filePlanComponentAPI.updateFilePlanComponent(filePlanComponent, folderId);
        restWrapper.assertStatusCodeIs(OK);
        return updatedComponent;
    }
    
    /**
     * Create RM user with given role
     * <br>
     * Checks whether the user exists in RM site and creates it if required, with password identical
     * to username. Note the role is a Core API role, not an RM role.
     * <br>
     * For already existing users, no role verification is performed.
     * <p>
     * @param userName username to add
     * @param userRole user's role
     * @throws Exception
     */
    public UserModel createRMUserWithRole(String userName, UserRole userRole) throws Exception
    {
        rmSiteAPI.usingRestWrapper().authenticateUser(dataUser.getAdminUser());
        String siteId = rmSiteAPI.getSite().getId();

        // check if user exists
        UserModel user = new UserModel();
        user.setUsername(userName);
        user.setPassword(userName);
 
        if (!dataUser.isUserInRepo(userName))
        {
            // user doesn't exist, create it
            user = dataUser.createUser(userName, userName);
            user.setUserRole(userRole);
            
            dataUser.addUserToSite(user, new SiteModel(siteId), userRole);
        }

        return user;
    }
}