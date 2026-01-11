import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { apiGetCalculator, apiSetCalculator } from "../../api";

export const fetchCalculator = createAsyncThunk("calculator/fetch", async () => {
    const data = await apiGetCalculator();
    return data.value;
});

export const saveCalculator = createAsyncThunk("calculator/save", async (value) => {
    const data = await apiSetCalculator(value);
    return data.value;
});

const calculatorSlice = createSlice({
    name: "calculator",
    initialState: { value: 0, status: "idle" },
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(fetchCalculator.fulfilled, (state, action) => {
                state.value = action.payload;
                state.status = "succeeded";
            })
            .addCase(saveCalculator.fulfilled, (state, action) => {
                state.value = action.payload;
            });
    },
});

export default calculatorSlice.reducer;
