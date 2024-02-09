import { SyntheticEvent, useState } from "react";

export default function RegisterAccountForm(){

    const [email, setEmail] = useState("");
    const handleEmailChange = (e: any) => {
        e.preventDefault();
        setEmail(e.target.value);
    }
    
    const submitEmail = async() => {
        const payload = JSON.stringify({email})
        const response = await fetch("http://localhost:8080/api/v2/account", {
            method: "POST",
            body: payload
        })
        return response.json();
    }

    const submitEmailChange = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        submitEmail();
    }

    return(<>
    <form onSubmit={submitEmailChange}>
        <label htmlFor="Email">Email:</label>
        <input onChange={handleEmailChange} type="Email"></input>
        <button type="submit">Submit</button>
    </form>
    </>);
}