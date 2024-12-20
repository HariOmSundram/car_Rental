package com.knowit.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Entity
@Table(name="mode_of_payment")

public class ModeOfPayment {
    @Id

    int modeid;
    String modename;
}
