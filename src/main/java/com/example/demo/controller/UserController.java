package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final String USER_LIST_FILE_PATH = "../../../../../resources/顧客データ";

    public static void main(String[] args) {

		UserController userController = new UserController();
        userController.execute();
    }

    public void execute() {
		
		try {
            List<User> userList = getUserList(USER_LIST_FILE_PATH);
        } catch (Exception e) {
			e.printStackTrace();
        }
    }

    private List<User> getUserList(String filePath) throws ParseException, FileNotFoundException, IOException {
		List<User> list = new ArrayList<>();
		List<String[]> csvList = loadFromFile(filePath);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		for (String[] cols : csvList) {
            String id = cols[0];
            String name = cols[1];
            String phoneNumber = cols[2];
			Date birthday = sdf1.parse(cols[3]);//Date型にフォーマット
			String email = cols[4];
            String postalCode = cols[5];
            String address = cols[6];
            int numberOfpurchases = Integer.parseInt(cols[7]);
            Date lastPurchaseDate = sdf2.parse(cols[8]);//Date型にフォーマット

			User users = new User(id, name, phoneNumber, birthday, email, postalCode, address, numberOfpurchases, lastPurchaseDate);//Userインスタンスを生成
			list.add(users);
		}
		return list;
	}
    
    private List<String[]> loadFromFile(String filePath) throws FileNotFoundException, IOException {
		List<String[]> list = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
			String line;
			int count = -1;
			while ((line = br.readLine()) != null) {
				count++;
				if (count == 0) {
					continue;
				}
				list.add(line.split(","));
			}
		}
		return list;
    }
    
    // 途中　ここまで

    
    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) {
        List<User> user = userService.findAll();
        model.addAttribute("user", user); 
        return "user/index";
    }

    @GetMapping("new")
    public String newUser(Model model) {
        return "user/new";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        User user = userService.findOne(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @GetMapping("{id}")
    public String show(@PathVariable String id, Model model) {
        User user = userService.findOne(id);
        model.addAttribute("user", user);
        return "user/show";
    }

    @PostMapping
    public String create(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/user";
    }

    @PutMapping("{id}")
    public String update(@PathVariable String id, @ModelAttribute User user) {
        user.setId(id);
        userService.save(user);
        return "redirect:/user";
    }

    @DeleteMapping("{id}")
    public String destroy(@PathVariable String id) {
        userService.delete(id);
        return "redirect:/user";
    }
}
