package com.task.project2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class MainController {
    private ExpenseDao expenseDao;

    MainController(ExpenseDao expenseDao) {
        this.expenseDao = expenseDao;
    }


    @GetMapping("/")
    public String main (){

        return "index";
    }


    @GetMapping("/dodaj")
    public String dodaj(Model model) {
        model.addAttribute("newExpense", new Expense());

        return "addExpenseForm";
    }


    @PostMapping("/addExpense")
    public String addExpense(Expense expense) {
        expenseDao.save(expense);

        return "redirect:/";
    }


    @GetMapping("/przychody")
    public String przychody(Model model) {
        List<Expense> expenses = expenseDao.read(true);
        model.addAttribute("expenses", expenses);

        return "expenses";
    }


    @GetMapping("/wydatki")
    public String wydatki(Model model) {
        List<Expense> expenses = expenseDao.read(false);
        model.addAttribute("expenses", expenses);

        return "expenses";
    }


    @GetMapping("/zakres")
    public String zakresForm(Model model) {
        model.addAttribute("newExpenseFind", new ExpenseFind());

        return "findForm";
    }


    @GetMapping("/findExpensesDate")
    public String findExpenses(ExpenseFind expenseFind, Model model) {
        List<Expense> expenses = expenseDao.readBetweenDates(expenseFind.getDateFrom(), expenseFind.getDateTo());
        model.addAttribute("expenses", expenses);

        return "expenses";
    }


    @GetMapping("/findExpensesAmount")
    public String findExpensesAmount(ExpenseFind expenseFind, Model model) {
        List<Expense> expenses = expenseDao.readAboveAmount(expenseFind.getAmountAbove());
        model.addAttribute("expenses", expenses);

        return "expenses";
    }
}


