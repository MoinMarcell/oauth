import './App.css'
import {useEffect, useState} from "react";
import axios from "axios";

function App() {
    const [appUser, setAppUser] = useState<string>();

    function login() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin

        window.open(host + '/oauth2/authorization/github', '_self')
    }

    function logout() {
        axios.post("/api/logout")
            .then(() => {
                setAppUser(undefined)
            })
            .catch(error => {
                console.error(error)
            })
    }

    useEffect(() => {
        axios.get("/api/v1/auth/me")
            .then((response) => {
                setAppUser(response.data);
            })
            .catch((e) => console.log(e));
    }, []);

    return (
        <>
            {appUser &&
                (
                    <>
                        <h1>Welcome back, {appUser}!</h1>
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
