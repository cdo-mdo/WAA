import { createSlice, nanoid } from "@reduxjs/toolkit";

const todosSlice = createSlice({
    name: "todos",
    initialState: {
        items: [
            { id: "t1", text: "Learn Redux basics" },
            { id: "t2", text: "Build Todo + Calculator" },
        ],
    },
    reducers: {
        addTodo: {
            reducer: (state, action) => {
                state.items.push(action.payload);
            },
            prepare: (text) => ({
                payload: { id: nanoid(), text },
            }),
        },
        removeTodo: (state, action) => {
            state.items = state.items.filter((t) => t.id !== action.payload);
        },
    },
});

export const { addTodo, removeTodo } = todosSlice.actions;
export default todosSlice.reducer;
