import { Link, useNavigate } from "react-router-dom";

function Navbar() {
    const navigate = useNavigate();

    const token = localStorage.getItem("token");

    // ✅ LOGOUT FUNCTION
    const logout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("userId");

        alert("Logged out");

        navigate("/login");
    };

    return (
        <div style={styles.nav}>
            <h2>Royal Hotels</h2>

            <div style={styles.links}>
                <Link to="/">Home</Link>

                {/* ✅ Show only if logged in */}
                {token && <Link to="/bookings">Bookings</Link>}

                {/* ❌ Show login if NOT logged in */}
                {!token && <Link to="/login">Login</Link>}

                {/* ✅ Show logout if logged in */}
                {token && (
                    <button onClick={logout} style={styles.logoutBtn}>
                        Logout
                    </button>
                )}
            </div>
        </div>
    );
}

const styles = {
    nav: {
        display: "flex",
        justifyContent: "space-between",
        padding: "15px 30px",
        background: "#111",
        color: "white"
    },
    links: {
        display: "flex",
        gap: "20px",
        alignItems: "center"
    },
    logoutBtn: {
        background: "red",
        color: "white",
        border: "none",
        padding: "5px 10px",
        cursor: "pointer"
    }
};

export default Navbar;