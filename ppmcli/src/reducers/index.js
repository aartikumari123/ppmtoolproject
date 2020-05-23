import { combineReducers } from "redux";
import errorReducer from './errorReducer';
import ProjectReducer from "./projectReducers";
export default combineReducers({
error: errorReducer,
projects: ProjectReducer
});