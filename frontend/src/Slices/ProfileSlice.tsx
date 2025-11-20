import {  createSlice } from "@reduxjs/toolkit";
const profileSlice = createSlice({
    name: 'profile',
    initialState:  {},
    reducers: {
        changeProfile: (state, action) => {
            return { ...state, ...action.payload };
        },
        setProfile: (state, action) => {
            state=action.payload;
            return state;
        }
    }
});
export const { changeProfile, setProfile } = profileSlice.actions;
export default profileSlice.reducer;