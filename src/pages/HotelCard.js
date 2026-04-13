import { useNavigate } from "react-router-dom";

function HotelCard({ hotel }) {
    const navigate = useNavigate();

    return (
        <div style={{ border: "1px solid gray", margin: "10px", padding: "10px" }}>
            <h3>{hotel.name}</h3>
            <p>{hotel.location}</p>

            <button onClick={() => navigate(`/hotel/${hotel.id}`)}>
                View Rooms
            </button>
        </div>
    );
}

export default HotelCard;