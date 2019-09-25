package com.twitter.utils;

import com.twitter.clients.StatusClient;
import com.twitter.service.StatusService;

public abstract class BaseTest {
    protected StatusClient statusClient = new StatusClient();
    protected StatusService statusService = new StatusService();
}
