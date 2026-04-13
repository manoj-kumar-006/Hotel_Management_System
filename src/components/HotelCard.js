import React from "react";
import { useNavigate } from "react-router-dom";

function HotelCard({ hotel }) {
    const navigate = useNavigate();

    return (
        <div
            style={styles.card}
            onMouseEnter={(e) => e.currentTarget.style.transform = "scale(1.05)"}
            onMouseLeave={(e) => e.currentTarget.style.transform = "scale(1)"}
        >
            <img
                src="https://images.unsplash.com/photo-1551882547-ff40c63fe5fa"
                style={styles.image}
                alt="hotel"
            />

            <h3>{hotel.name}</h3>
            <p>{hotel.location}</p>
            <p style={{ fontWeight: "bold" }}>₹2000 / night</p>

            <button onClick={() => navigate(`/hotel/${hotel.id}`)} style={styles.btn}>
                Book Now
            </button>
        </div>
    );
}

const styles = {
    card: {
        width: "260px",
        margin: "15px",
        padding: "10px",
        borderRadius: "15px",
        background: "#fff",
        boxShadow: "0 4px 15px rgba(0,0,0,0.2)",
        transition: "0.3s"
    },
    image: {
        width: "100%",
        height: "150px",
        borderRadius: "10px"
    },
    btn: {
        marginTop: "10px",
        background: "linear-gradient(to right, #f4c430, #ffcc00)",
        border: "none",
        padding: "10px",
        borderRadius: "8px",
        cursor: "pointer"
    }
};

export default HotelCard;