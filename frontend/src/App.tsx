import './App.css'
import {useEffect, useState} from "react";
import axios from "axios";
import {Navigate} from "react-router-dom";

type AppUser = {
    username: string,
    avatarUrl: string,
    githubId: number,
}

function App() {
    const [appUser, setAppUser] = useState<AppUser | null | undefined>(undefined);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);
        axios.get("/api/v1/auth/me")
            .then((response) => {
                setAppUser(response.data);
            })
            .catch((e) => {
                setAppUser(null);
                console.log(e)
                return <Navigate to={"http://localhost:8080/oauth2/authorization/github"}/>;
            })
            .finally(() => {
                setLoading(false);
            });
    }, []);

    return (
        <>
            <h1>OAuth-Demo</h1>
            {loading && <p>Loading...</p>}
            {appUser && <p>Logged in as {appUser.username}</p>}
            {appUser && <img src={appUser.avatarUrl} alt="avatar"/>}
            {!appUser && <p>Not logged in</p>}
        </>
    )
}

export default App
