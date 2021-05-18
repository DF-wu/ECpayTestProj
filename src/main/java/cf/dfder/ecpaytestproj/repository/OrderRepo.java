package cf.dfder.ecpaytestproj.repository;

import cf.dfder.ecpaytestproj.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface OrderRepo extends MongoRepository<Order, String> {
}