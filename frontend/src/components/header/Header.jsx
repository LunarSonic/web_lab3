import React from "react";
import "../../styles/style.css";

export function Header() {
    return (
        <header id="lab">
            <h1>Веб-программирование. Лабораторная работа №3. Вариант 464900</h1>
            <div id="container_with_image">
                <h2>Абдуллаева София Улугбековна. P3208</h2>
                <a href="https://github.com/LunarSonic/web_lab3" target="_blank" rel="noreferrer">
                    <img id="githubIcon" src="/images/github.svg" alt=""/>
                </a>
            </div>
        </header>
    );
}