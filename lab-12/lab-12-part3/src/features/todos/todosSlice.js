import { createSlice, nanoid } from "@reduxjs/toolkit";

const initialState = {
    items: [
        { id: "1", text: "Learn Redux" },
        { id: "2", text: "Build Todo App" },
    ],
};

const todosSlice = createSlice({
    name: "todos",
    initialState,
    reducers: {
        addTodo: {
            reducer(state, action) {
                state.items.push(action.payload);
            },
            prepare(text) {
                return {
                    payload: {
                        id: nanoid(),
                        text,
                    },
                };
            },
        },
        removeTodo(state, action) {
            const id = action.payload;
            state.items = state.items.filter((t) => t.id !== id);
        },
    },
});

export const { addTodo, removeTodo } = todosSlice.actions;
export default todosSlice.reducer;
