package com.ops.system.tower.zookeeper.controller;

import com.ops.system.tower.zookeeper.service.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zoo")
public class ZooController {
    @Autowired
    private ZooService zookeeperService;

    @GetMapping("/list-nodes")
    public String listNodes(){
        try {
            zookeeperService.runZookeeperCliCommand("ls /");
            return "Nodes listed!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/create-node")
    public String createNode() {
        try {
            zookeeperService.createNode("/my-node", "data");
            return "Node created successfully!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }



}
