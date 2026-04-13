import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function Login() {
    const navigate = useNavigate();

    const [isLogin, setIsLogin] = useState(true);
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const login = async (e) => {
        e.preventDefault();

        try {
            const res = await API.post("/auth/login", { email, password });

            console.log(res.data); // ✅ inside try

            localStorage.setItem("token", res.data.token || res.data);
            localStorage.setItem("userId", res.data.userId || 1);

            alert("Login successful");

            navigate("/");
        } catch (err) {
            console.error(err);
            alert("Login failed");
        }
    };

    // ✅ REGISTER
    const register = async (e) => {
        e.preventDefault(); // 🔥 prevents reload

        try {
            await API.post("/auth/register", {
                name,
                email,
                password,
            });

            alert("Registration successful! Please login.");
            setIsLogin(true);
        } catch {
            alert("Registration failed");
        }
    };

    return (
        <div style={{ padding: "20px" }}>
            <h2>{isLogin ? "Login" : "Register"}</h2>

            {/* NAME (ONLY REGISTER) */}
            {!isLogin && (
                <input
                    placeholder="Name"
                    onChange={(e) => setName(e.target.value)}
                />
            )}

            <input
                placeholder="Email"
                onChange={(e) => setEmail(e.target.value)}
            />

            <input
                type="password"
                placeholder="Password"
                onChange={(e) => setPassword(e.target.value)}
            />

            <br /><br />

            {/* ✅ BUTTON FIXED */}
            <button
                type="button"
                onClick={isLogin ? login : register}
            >
                {isLogin ? "Login" : "Register"}
            </button>

            <p
                style={{ marginTop: "10px", cursor: "pointer", color: "blue" }}
                onClick={() => setIsLogin(!isLogin)}
            >
                {isLogin
                    ? "Don't have an account? Register"
                    : "Already have an account? Login"}
            </p>
        </div>
    );
}

export default Login;