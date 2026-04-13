import React from "react";

function Footer() {
    return (
        <div style={styles.footer}>
            <p>© 2026 Hotel Booking System</p>
        </div>
    );
}

const styles = {
    footer: {
        marginTop: "20px",
        padding: "20px",
        background: "#eee",
        textAlign: "center"
    }
};

export default Footer;