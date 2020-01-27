package me.hackusatepvp.fall.staff;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Staff {
    private UUID uuid;
    private String name;

    public Staff(UUID uuid) {
        this.uuid = uuid;
    }
}
