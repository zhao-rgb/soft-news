package com.soft1851.files;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zhao
 * @className GirdFsConfig
 * @Description TODO
 * @Date 2020/11/21
 * @Version 1.0
 **/
@Component
public class GridFsConfig {

    @Value("${spring.data.mongodb.database}")
    private String mongodb;

    @Bean
    public GridFSBucket gridFsBucket(MongoClient mongoClient) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(mongodb);
        return GridFSBuckets.create(mongoDatabase);
    }
}
