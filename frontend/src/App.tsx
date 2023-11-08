import './App.css'
import {useEffect, useState} from "react";
import axios from "axios";
import {DateTime} from 'luxon';

type AppUser = {
    username: string,
    registrationDate: string,
    role: "USER" | "ADMIN",
}

function App() {
    const [appUser, setAppUser] = useState<AppUser | undefined>(undefined);

    function login() {
        window.open("http://localhost:8080/oauth2/authorization/github", "_self");
    }

    function logout() {
        axios.post("/api/v1/auth/logout")
            .then(() => {
                setAppUser(undefined);
            })
            .catch(() => {
                console.log("Logout failed!");
            });
    }

    useEffect(() => {
        axios.get("/api/v1/auth/me")
            .then((response) => {
                setAppUser(response.data);
            })
            .catch((e) => console.log(e));
    }, []);

    let date;
    if (appUser) {
        date = DateTime.fromISO(appUser.registrationDate, {zone: 'Europe/Berlin'}).toFormat('yyyy/MM/dd');
    }

    return (
        <>
            {appUser &&
                (
                    <>
                        <h1>Welcome back, {appUser.username}!</h1>
                        <p>You are registered since {date} and your role is {appUser.role}.</p>
                        <button onClick={logout}>Logout</button>
                    </>
                )}
            {!appUser && (
                <>
                    <h1>Welcome!</h1>
                    <button onClick={login}>Login with GitHub</button>
                </>
            )}
        </>
    )
}

export default App
