package com.example.appdevf2.paraderooct17.Repository;
import com.example.appdevf2.paraderooct17.Entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
    List<NotificationEntity> findByUser_UsersidOrderByCreatedAtDesc(int userId);
}
