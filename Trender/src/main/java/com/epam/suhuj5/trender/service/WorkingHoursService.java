package com.epam.suhuj5.trender.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.epam.suhuj5.trender.repository.WorkingHours;
 
@Repository
public class WorkingHoursService {
     
    @Autowired
    private MongoTemplate mongoTemplate;
     
    public static final String COLLECTION_NAME = "workinghours";
     
    public void addWorkingHours(WorkingHours workingHours) {
        if (!mongoTemplate.collectionExists(WorkingHours.class)) {
            mongoTemplate.createCollection(WorkingHours.class);
        }       
        workingHours.setId(UUID.randomUUID().toString());
        mongoTemplate.insert(workingHours, COLLECTION_NAME);
    }
     
    public List<WorkingHours> listWorkingHours() {
        return mongoTemplate.findAll(WorkingHours.class, COLLECTION_NAME);
    }
     
    public void deleteWorkingHours(WorkingHours workingHours) {
        mongoTemplate.remove(workingHours, COLLECTION_NAME);
    }
     
    public void updateWorkingHours(WorkingHours workingHours) {
        mongoTemplate.insert(workingHours, COLLECTION_NAME);      
    }
}