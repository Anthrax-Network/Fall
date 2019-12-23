package me.hackusatepvp.fall.vouchers.impl.kits;

import me.hackusatepvp.fall.vouchers.Voucher;

import java.util.Optional;

public class SaberKit extends Voucher {

    public SaberKit() {
        super("Saber");
    }

    @Override
    public String getName() {
       return "Saber";
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public Optional<Integer> getCoast() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getRank() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getKit() {
        return Optional.of("Saber");
    }
}
