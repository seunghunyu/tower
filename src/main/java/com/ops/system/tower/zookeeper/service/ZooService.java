package com.ops.system.tower.zookeeper.service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZooService {

    private final CuratorFramework client;

    public ZooService(){
        String zookeeperAddress = "192.168.12.224:2181,192.168.12.225:2181,192.168.12.226:2181";
        this.client = CuratorFrameworkFactory.newClient(zookeeperAddress, new ExponentialBackoffRetry(1000,3));        this.client.start();
    }
    // Zookeeper에서 노드를 생성하는 예시
    public void createNode(String path, String data) throws Exception {
        if (client.checkExists().forPath(path) == null) {
            client.create().forPath(path, data.getBytes());
            System.out.println("Node created: " + path);
        }
    }

    // Zookeeper에서 데이터를 읽어오는 예시
    public String getData(String path) throws Exception {
        byte[] data = client.getData().forPath(path);
        return new String(data);
    }

    // Zookeeper에서 CLI 명령어 실행 예시
    public void runZookeeperCliCommand(String command) throws Exception {
        // 예시로, Zookeeper의 데이터에 대한 작업을 CLI 명령어로 실행한다고 가정
        // client.getData() 또는 client.setData() 등을 사용하여 구현
        if (command.equals("ls /")) {
            // / 경로의 데이터를 가져오는 예시
            List<String> children = client.getChildren().forPath("/");
            for (String child : children) {
                System.out.println(child);
            }
        }
    }
}
