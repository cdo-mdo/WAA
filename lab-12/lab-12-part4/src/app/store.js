import { configureStore } from "@reduxjs/toolkit";
import calculatorReducer from "../features/calculator/calculatorSlice";
import todosReducer from "../features/todos/todosSlice";

export const store = configureStore({
    reducer: {
        calculator: calculatorReducer,
        todos: todosReducer,
    },
});
