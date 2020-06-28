package com.cvgenerator.domain.entity;


import com.cvgenerator.domain.enums.TokenType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SmsToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime expiryDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    String value;
}
