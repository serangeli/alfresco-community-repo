/*-
 * #%L
 * alfresco-tas-restapi
 * %%
 * Copyright (C) 2005 - 2022 Alfresco Software Limited
 * %%
 * This file is part of the Alfresco software. 
 * If the software was purchased under a paid Alfresco license, the terms of 
 * the paid license agreement will prevail.  Otherwise, the software is 
 * provided under the following open source license terms:
 * 
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package org.alfresco.rest.model;

import org.alfresco.rest.core.IRestModel;
import org.alfresco.rest.core.assertion.ModelAssertion;
import org.alfresco.utility.model.TestModel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Generated by 'aforascu' on '2018-01-10 16:02' from 'Alfresco Content Services REST API' swagger file 
 * Generated from 'Alfresco Content Services REST API' swagger file
 * Base Path {@linkplain /alfresco/api}
 */
public class RestEntitlementsInfoModel extends TestModel implements IRestModel<RestEntitlementsInfoModel>
{
    @Override
    public ModelAssertion<RestEntitlementsInfoModel> assertThat()
    {
        return new ModelAssertion<RestEntitlementsInfoModel>(this);
    }

    @Override
    public ModelAssertion<RestEntitlementsInfoModel> and()
    {
        return assertThat();
    }

    @JsonProperty(value = "entry")
    RestEntitlementsInfoModel model;

    @Override
    public RestEntitlementsInfoModel onModel()
    {
        return model;
    }


    private int maxUsers;	    

    private int maxDocs;	    

    private boolean isClusterEnabled;	    

    private boolean isCryptodocEnabled;	    

    public int getMaxUsers()
    {
        return this.maxUsers;
    }

    public void setMaxUsers(int maxUsers)
    {
        this.maxUsers = maxUsers;
    }				

    public int getMaxDocs()
    {
        return this.maxDocs;
    }

    public void setMaxDocs(int maxDocs)
    {
        this.maxDocs = maxDocs;
    }				

    public boolean getIsClusterEnabled()
    {
        return this.isClusterEnabled;
    }

    public void setIsClusterEnabled(boolean isClusterEnabled)
    {
        this.isClusterEnabled = isClusterEnabled;
    }				

    public boolean getIsCryptodocEnabled()
    {
        return this.isCryptodocEnabled;
    }

    public void setIsCryptodocEnabled(boolean isCryptodocEnabled)
    {
        this.isCryptodocEnabled = isCryptodocEnabled;
    }				
}
 
