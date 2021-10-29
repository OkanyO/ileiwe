package com.ileiwe.ileiwe.data.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearningParty{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotNull @NotBlank @NotEmpty
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    @NotBlank @NotNull
    private String password;
    private boolean enabled;
    @CreationTimestamp
    private LocalDate dateCreated;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Authority> authorities;

    public  LearningParty(String email, String password, Authority authority){

        this.email = email;
        this.password = password;
        addAuthority(authority);
        this.enabled = false;
    }



    private  void  addAuthority(Authority authority){
        if (this.authorities ==null){
            this.authorities = new ArrayList<>();
        }
        this.authorities.add(authority);
    }
}
