import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    value: 0,
};

const calculatorSlice = createSlice({
    name: "calculator",
    initialState,
    reducers: {
        add: (state, action) => {
            state.value += action.payload;
        },
        subtract: (state, action) => {
            state.value -= action.payload;
        },
        multiply: (state, action) => {
            state.value *= action.payload;
        },
        reset: (state) => {
            state.value = 0;
        },
    },
});
export const { add, subtract, multiply, reset } = calculatorSlice.actions;
export default calculatorSlice.reducer;