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
package org.alfresco.rest.search;

import java.util.List;

import org.alfresco.rest.core.IRestModel;
import org.alfresco.rest.core.assertion.ModelAssertion;
import org.alfresco.utility.model.TestModel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Generated by 'gethin' on '2017-03-17 12:44' from 'Alfresco Search REST API' swagger file 
 * Generated from 'Alfresco Search REST API' swagger file
 * Base Path {@linkplain /alfresco/api/-default-/public/search/versions/1}
 */
public class RestGenericFacetResponseModel extends TestModel implements IRestModel<RestGenericFacetResponseModel>
{
    @Override
    public ModelAssertion<RestGenericFacetResponseModel> assertThat()
    {
        return new ModelAssertion<RestGenericFacetResponseModel>(this);
    }

    @Override
    public ModelAssertion<RestGenericFacetResponseModel> and()
    {
        return assertThat();
    }

    @JsonProperty(value = "entry")
    RestGenericFacetResponseModel model;

    @Override
    public RestGenericFacetResponseModel onModel()
    {
        return model;
    }

    /**
    The facet type, eg. interval, range, field, query1
    */	        

    private String type;	    
    /**
    The field name or its explicit label, if provided on the request
    */	        

    private String label;	    
    /**
    An array of buckets and values
    */	        

    private List<RestGenericBucketModel> buckets;	    

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }				

    public String getLabel()
    {
        return this.label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }				

    public List<RestGenericBucketModel> getBuckets()
    {
        return this.buckets;
    }

    public void setBuckets(List<RestGenericBucketModel> buckets)
    {
        this.buckets = buckets;
    }				
}
 
