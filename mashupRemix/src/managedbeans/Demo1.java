/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author hazems
 *
 */
@ManagedBean
@RequestScoped
public class Demo1 {
    List<Place> places = new ArrayList<Place>();

    // Initialize with some places ...
    {
        getPlaces().add(new Place("Pittsburg - New Hampshire", "Pittsburg, New Hampshire, USA", "03592", "New Hampshire"));
        getPlaces().add(new Place("New Era - Michigan", "New Era, Michigan, USA", "49446", "Michigan"));
        getPlaces().add(new Place("Fombell - Pennsylvania", "Fombell, Pennsylvania, USA", "16123", "Pennsylvania"));
        getPlaces().add(new Place("Monroe - Louisiana", "Monroe, Louisiana, USA", "71207", "Louisiana"));
        getPlaces().add(new Place("Charter Oak - Iowa", "Charter Oak, Iowa, USA", "51439", "Iowa"));
    }

    public Demo1(){
    }

    /**
     * @return the places
     */ 
    public List<Place> getPlaces() {
        return places;
    }

    /**
     * @param places the places to set
     */ 
    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
