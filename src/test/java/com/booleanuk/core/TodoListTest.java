package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TodoListTest {
    @Test
    public void exampleTest() {
        String hello = "Hello";
        Assertions.assertEquals("Hello", hello);
        Assertions.assertNotEquals("Goodbye", hello);
    }

    @Test
    public void returnTrueAddTaskNotInList() {
        TodoList todoList = new TodoList();
        Assertions.assertTrue(todoList.add("Vacuum"));
    }

    @Test
    public void returnFalseAddTaskAlreadyInList() {
        TodoList todoList = new TodoList();
        todoList.add("Vacuum");
        Assertions.assertFalse(todoList.add("Vacuum"));
    }

    @Test
    public void addingMultipleTasksWorkCorrectly() {
        TodoList todoList = new TodoList();
        Assertions.assertTrue(todoList.add("Vacuum"));
        Assertions.assertTrue(todoList.add("Laundry"));
        Assertions.assertTrue(todoList.add("Go for a walk"));
        Assertions.assertFalse(todoList.add("Laundry"));
    }

    @Test
    public void returnTrueRemoveTaskInList() {
        TodoList todoList = new TodoList();
        todoList.add("Vacuum");
        Assertions.assertTrue(todoList.remove("Vacuum"));
    }

    @Test
    public void returnFalseRemoveTaskNotInList() {
        TodoList todoList = new TodoList();
        Assertions.assertFalse(todoList.remove("Vacuum"));
    }

    @Test
    public void removingMultipleTasksWorkCorrectly() {
        TodoList todoList = new TodoList();
        todoList.add("Vacuum");
        todoList.add("Laundry");
        Assertions.assertTrue(todoList.remove("Vacuum"));
        Assertions.assertFalse(todoList.remove("Go for a walk"));
        Assertions.assertTrue(todoList.remove("Laundry"));
    }

    @Test
    public void returnTrueChangeStatusOfExistingTask() {
        TodoList todoList = new TodoList();
        todoList.add("Vacuum");
        Assertions.assertTrue(todoList.changeStatus("Vacuum"));
        Assertions.assertTrue(todoList.changeStatus("Vacuum"));
    }

    @Test
    public void returnFalseChangeStatusOfNonExistingTask() {
        TodoList todoList = new TodoList();
        todoList.add("Vacuum");
        Assertions.assertFalse(todoList.changeStatus("Laundry"));
    }

    @Test
    public void testCorrectOutputFromGetTask() {
        TodoList todoList = new TodoList();
        todoList.add("Vacuum");

        String expectedOutput = "Vacuum is incomplete.";
        Assertions.assertEquals(expectedOutput, todoList.getTask("Vacuum"));

        todoList.changeStatus("Vacuum");
        expectedOutput = "Vacuum is complete.";
        Assertions.assertEquals(expectedOutput, todoList.getTask("Vacuum"));

        expectedOutput = "Laundry wasn't found.";
        Assertions.assertEquals(expectedOutput, todoList.getTask("Laundry"));
    }

    @Test
    public void testCorrectOutputFromGetTasks() {
        TodoList todoList = new TodoList();
        todoList.add("Vacuum");
        todoList.add("Go for a walk");
        todoList.changeStatus("Go for a walk");
        todoList.add("Laundry");
        String expectedOutput = "Vacuum incomplete\nGo for a walk complete\nLaundry incomplete\n";
        Assertions.assertEquals(expectedOutput, todoList.getTasks());
    }

    @Test
    public void testReturnEmptyStringFromGetTasks() {
        TodoList todoList = new TodoList();
        String expectedOutput = "";
        Assertions.assertEquals(expectedOutput, todoList.getTasks());
    }

    @Test
    public void testGetCompleteTasksWithGetStatusMethod() {
        TodoList todoList = new TodoList();
        todoList.add("Vacuum");
        todoList.add("Go for a walk");
        todoList.changeStatus("Go for a walk");
        todoList.add("Laundry");
        todoList.add("Make dinner");
        todoList.changeStatus("Make dinner");
        ArrayList<String> completedTasks = new ArrayList<>();
        completedTasks.add("Make dinner");
        completedTasks.add("Go for a walk");
        Assertions.assertEquals(completedTasks, todoList.getStatus(true));
    }

    @Test
    public void testGetIncompleteTasksWithGetStatusMethod() {
        TodoList todoList = new TodoList();
        todoList.add("Vacuum");
        todoList.add("Go for a walk");
        todoList.changeStatus("Go for a walk");
        todoList.add("Laundry");
        todoList.add("Make dinner");
        todoList.changeStatus("Make dinner");
        ArrayList<String> incompletedTasks = new ArrayList<>();
        incompletedTasks.add("Vacuum");
        incompletedTasks.add("Laundry");
        Assertions.assertEquals(incompletedTasks, todoList.getStatus(false));
    }

    @Test
    public void testGetStatusWithEmptyList() {
        TodoList todoList = new TodoList();
        Assertions.assertEquals(new ArrayList<>(), todoList.getStatus(true));
        Assertions.assertEquals(new ArrayList<>(), todoList.getStatus(false));
    }

    @Test
    public void checkListOrderedAlphabeticallyInAscendingOrder() {
        TodoList todoList = new TodoList();
        todoList.add("Vacuum");
        todoList.add("Go for a walk");
        todoList.add("Laundry");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Go for a walk");
        expected.add("Laundry");
        expected.add("Vacuum");
        Assertions.assertEquals(expected, todoList.getTasksOrdered(true));
    }

    @Test
    public void checkListOrderedAlphabeticallyInDescendingOrder() {
        TodoList todoList = new TodoList();
        todoList.add("Vacuum");
        todoList.add("Go for a walk");
        todoList.add("Laundry");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Vacuum");
        expected.add("Laundry");
        expected.add("Go for a walk");
        Assertions.assertEquals(expected, todoList.getTasksOrdered(false));
    }

    @Test
    public void checkOrderListWithEmptyList(){
        TodoList todoList = new TodoList();
        Assertions.assertEquals(new ArrayList<>(), todoList.getTasksOrdered(true));
        Assertions.assertEquals(new ArrayList<>(), todoList.getTasksOrdered(false));
    }
}
