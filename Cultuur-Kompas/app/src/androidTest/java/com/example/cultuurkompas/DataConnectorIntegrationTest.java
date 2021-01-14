package com.example.cultuurkompas;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;

import com.example.cultuurkompas.activities.MapScreenActivity;
import com.example.cultuurkompas.data.datamodel.Route;
import com.example.cultuurkompas.interfaces.DataConnector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DataConnectorIntegrationTest {

    private DataConnector dataConnector;
    private List<Route> routes = new ArrayList<>();

    @Before
    public void createNewDataConnector(){
        dataConnector = DataConnector.getInstance();
        routes = this.dataConnector.getRoutes();
    }

    //Can't be tested through code due to Activity and Shared preferences construction of the dataConnector.getAllData() method.
    @Test
    public void testRouteCount(){
        //Check amount of routes.
        Assert.assertEquals(3,routes.size());
    }

    //Can't be tested through code due to Activity and Shared preferences construction of the dataConnector.getAllData() method.
    @Test
    public void testRouteFinished(){
        //Finish route.
        Route route = routes.get(0);
        boolean startingState = route.isFinished();
        boolean endingState = false;
        for (int i = 0; i < route.getWaypoints().size();i++){
            //Act as if the user walks around the waypoints.
               endingState = route.incrementProgressionCounter();
        }
        Assert.assertEquals(startingState,endingState);
    }
}
