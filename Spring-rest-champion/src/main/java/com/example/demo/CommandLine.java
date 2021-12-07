package com.example.demo;

import com.example.demo.champion.service.ChampionService;
import com.example.demo.champion.service.RaceService;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {

    private RaceService raceService;

    private ChampionService championService;

    private UserService userService;

    @Autowired
    public CommandLine(RaceService raceService, ChampionService championService, UserService userService) {
        this.raceService = raceService;
        this.championService = championService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner option_scanner = new Scanner(System.in);
        Scanner category_scanner = new Scanner(System.in);
        Scanner deletion_scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Options: ");
            System.out.println();
            System.out.println("a) List all elements ");
            System.out.println("b) List all categories ");
            System.out.println("c) Add new element to certain category ");
            System.out.println("d) Delete existing element ");
            System.out.println("e) Stop the application ");
            System.out.println("Choose your option: ");

            char option_choice = option_scanner.next().charAt(0);

            switch (option_choice) {
                case 'a':
                    championService.findAll().forEach(System.out::println);
                    break;
                case 'b':
                    System.out.println("Categories: ");
                    System.out.println("User, Character, Race ");
                    break;
                case 'c':


                    break;
                case 'd':
                    System.out.println("Choose category: ");
                    System.out.println("a) User ");
                    System.out.println("b) Character ");
                    System.out.println("c) Race ");
                    char category_choice = category_scanner.next().charAt(0);

                    System.out.println("Pass the name or id of the object to delete: ");
                    String deletion_name = deletion_scanner.next();
                    System.out.println();

                    switch (category_choice) {
                        case 'a':
//                            userService.find(deletion_name)
//                                    .ifPresentOrElse(
//                                            user -> userService.delete(user)
//                                            , () -> {
//                                                throw new IllegalArgumentException();
//                                            }
//                                    );
                            break;
                        case 'b':
//                        championService.find(Long.parseLong(deletion_name))
//                                .ifPresentOrElse(character ->
//                                                championService.delete(character)
//                                        , () -> {
//                                            throw new IllegalArgumentException();
//                                        }
//                                );
                            break;
                        case 'c':
//                            raceService.find(deletion_name)
//                                    .ifPresentOrElse(race -> raceService.delete(race)
//                                            , () -> {
//                                                throw new IllegalArgumentException();
//                                            }
//                                    );
//                            break;
                    }
                    break;
            }

        }
    }
}
