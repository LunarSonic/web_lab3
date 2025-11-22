import React from "react";
import {MainContainer} from "../mainContainer/MainContainer";
import {Header} from "../header/Header";
import {LogoutButton} from "../header/LogoutButton";
import {MainForm} from "../mainForm/MainForm";

export function MainPage() {
    return (
        <MainContainer>
            <Header/>
            <LogoutButton/>
            <MainForm />
        </MainContainer>
    );
}