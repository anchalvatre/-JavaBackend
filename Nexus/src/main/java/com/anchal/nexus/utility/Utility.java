package com.anchal.nexus.utility;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import com.anchal.nexus.model.User;
import com.anchal.nexus.model.Wallet;

public class Utility {

    private final Logger log = Logger.getLogger(Utility.class.getName());

    public void createuser(User user) throws IOException {
        Files.writeString(Path.of("user.csv"), user.toString() + System.lineSeparator(), StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    public void createWalletCSV(Wallet wallet) throws IOException {
        Files.writeString(Path.of("wallet.csv"), wallet.toString(), StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    public Optional<User> findUserByUserName(String userName) throws IOException {
        List<String> users = Files.readAllLines(Path.of("user.csv"));
        log.info("usres {} " + users);
        User userObj = null;
        for (String user : users) {
            String[] cols = user.split(",");
            // System.out.println(Arrays.toString(cols));
            if (cols[1].trim().equals(userName)) {
                Wallet wallet = findByWalletId(UUID.fromString(cols[2].trim())).orElseThrow(() -> {
                    throw new IllegalStateException("wallet is null");
                });
                userObj = new User(UUID.fromString(cols[0].trim()), cols[1].trim(), wallet);
            }
        }
        log.info("user is " + userObj);
        return Optional.ofNullable(userObj);
    }

    public Optional<Wallet> findByWalletId(UUID walletId) throws IOException {
        List<String> walletEntries = Files.readAllLines(Path.of("wallet.csv"));
        Wallet wallet = null;
        for (String entries : walletEntries) {
            String[] entry = entries.split(",");
            if (UUID.fromString(entry[0].trim()).equals(walletId)) {
                wallet = new Wallet(UUID.fromString(entry[0].trim()), new BigDecimal(entry[1]));
            }
        }
        return Optional.ofNullable(wallet);
    }

}
