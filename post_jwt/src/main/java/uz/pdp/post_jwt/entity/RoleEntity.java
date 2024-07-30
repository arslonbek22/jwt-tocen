package uz.pdp.post_jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.post_jwt.entity.enums.RoleName;

@AllArgsConstructor
@NoArgsConstructor
@Data
/*
@Entity
@Table(name = "roles")*/
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleName name;
}
