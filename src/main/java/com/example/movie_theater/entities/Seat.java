package com.example.movie_theater.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"hall_id", "seatNumber"}) // 🔥 Đảm bảo không có số ghế trùng trong cùng một phòng chiếu
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @Column(nullable = false)
    private String seatNumber; // 🔥 VD: "A1", "B5", "C10"

    @ManyToOne
    @JoinColumn(name = "seat_template_id", nullable = false) // 🔥 Xác định loại ghế (VIP, Standard, Couple...)
    private SeatTemplate seatTemplate;
}
