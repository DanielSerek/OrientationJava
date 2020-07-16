package com.foxclub.services;

import com.foxclub.models.Drink;
import com.foxclub.models.Food;
import com.foxclub.models.Fox;
import com.foxclub.models.User;
import com.foxclub.repository.FoxRepository;
import com.foxclub.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FoxServiceImpl implements FoxService {

    private UsersRepository usersRepository;
    private FoxRepository foxRepository;

    @Autowired
    public FoxServiceImpl(UsersRepository usersRepository, FoxRepository foxRepository) {
        this.usersRepository = usersRepository;
        this.foxRepository = foxRepository;
    }

    @Override
    public Fox getAFox(long id) {
        return this.foxRepository.findAll().stream().filter(x -> x.getUser().getId()==id).findFirst().orElse(null);
    }

    public Fox getAFox(String name){
       return this.foxRepository.findAll().stream().filter(x -> x.getUser().getUserName().equals(name)).findFirst().orElse(null);
    }

//    public long checkUserExists(String name) {
//        if (getAFox(id) != null) {
//            if (getAFox(id).getUser().getUserName().equals(name)) {
//                return true;
//            }
//        }
//        return false;
//    }

    public void changeNutrition(String name, String food, String drink) {
        Fox fox = getAFox(name);
        if(fox == null) {
            return;
        }
        Food previousFood = fox.getFood();
        fox.setFood(Food.valueOf(food));
        if (previousFood != Food.valueOf(food)) {
            fox.addLog(getTimeStamp() + " : Food has been changed from: " + previousFood.toString() + " to: " + food);
        }
        Drink previousDrink = fox.getDrink();
        fox.setDrink(Drink.valueOf(drink));
        if (previousDrink != Drink.valueOf(drink)) {
            fox.addLog(getTimeStamp() + " : Drink has been changed from: " + previousDrink.toString() + " to: " + drink);
        }
        foxRepository.save(fox);
    }

    public void learnATrick(String name, String trick) {
        Fox fox = getAFox(name);
        if(fox == null) {
            return;
        }
        if (!(fox.getTricks().contains(trick))) {
            fox.addTrick(trick);
            fox.addLog(getTimeStamp() + " : Learned to: " + trick);
        }
        foxRepository.save(fox);
    }

    public List<String> getTricks(String name) {
//        List<String> unavailable = this.foxRepository.findAll().stream()
//                .filter(e -> (getAFox(name).getTricks().stream()
//                        .filter(d -> d.equals(e))
//                        .count()) < 1)
//                .collect(Collectors.toList());
        List<String> tricksToLearn = new ArrayList();
        List<String> alltricks = new ArrayList<>(Arrays.asList("shoplifting", "raping", "killing", "torturing", "fighting", "kidnapping"));
        Fox fox = getAFox(name);
        for (String trick : fox.getTricks()) {
            if (!alltricks.contains(trick)) {
                tricksToLearn.add(trick);
            }
        }
        return tricksToLearn;
    }

    public boolean allSkillsLearned(String name) {
//        for (String trick : database.getTricks()) {
//            for (int i = 0; i < getAFox(name).getTricks().size(); i++) {
//                if(!getAFox(name).getTricks().contains(trick)) return false;
//            }
//        }
//        return true;
        return this.foxRepository.findAll().stream().map(x -> x.getTricks()).allMatch(getAFox(name).getTricks()::contains);
    }

    public Food getDefaultFood(String name) {
        if (getAFox(name) == null){
            return null;
        }
        return getAFox(name).getFood();
    }

    public Drink getDefaultDrink(String name) {
        if (getAFox(name) == null){
            return null;
        }
        return getAFox(name).getDrink();
    }

    public List<String> getLogs(String name) {
        if (getAFox(name) == null){
            return null;
        }
        return getAFox(name).getLogs();
    }

    @Override
    public void addANewUser(User user) {
        Fox fox = new Fox(user.getUserName(),Arrays.asList("sleep"), Food.Pasta, Drink.Beer);
        foxRepository.save(fox);
        fox.setUser(user);
        usersRepository.save(user);
    }

    @Override
    public void addANewFox(Fox fox) {
        foxRepository.save(fox);
    }

    @Override
    public long getUserId(String userName, String password) {
        for (User user : usersRepository.findAll()) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return user.getUserId();
            }
        }
        return -1;
    }

    private String getTimeStamp() {
        String pattern = "yyyy. MMMM dd. HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }
}
