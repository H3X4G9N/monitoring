package com.datalogging.user;

import com.datalogging.user.components.AuthorizationComponent;
import com.datalogging.user.components.BrowserComponent;
import com.datalogging.user.components.DataLoggingComponent;
import com.datalogging.user.components.ManagementComponent;
import com.datalogging.user.components.datalogger.DataLoggerComponent;
import com.datalogging.user.components.group.GroupComponent;
import com.datalogging.user.components.group.GroupItemComponent;

public class ComponentMap {
    public DataLoggingComponent dataLoggingComponent;
    public AuthorizationComponent authorizationComponent;
    public ManagementComponent managementComponent;
    public GroupItemComponent rootGroupItemComponent;
    public BrowserComponent browserComponent;
    public GroupComponent rootGroupComponent;
    public GroupComponent groupComponent;
    public DataLoggerComponent dataLoggerComponent;
}
