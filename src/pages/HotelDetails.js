import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import API from "../services/api";

function HotelDetails() {
    const { id } = useParams();

    const [rooms, setRooms] = useState([]);
    const [loading, setLoading] = useState(true);

    const [checkIn, setCheckIn] = useState("");
    const [checkOut, setCheckOut] = useState("");

    useEffect(() => {
        API.get(`/rooms/hotel/${id}`)
            .then((res) => {
                setRooms(res.data);
                setLoading(false);
            })
            .catch(() => setLoading(false));
    }, [id]);

    const bookRoom = async (roomId) => {
        const userId = localStorage.getItem("userId");

        if (!userId) {
            alert("⚠️ Please login first");
            return;
        }

        if (!checkIn || !checkOut) {
            alert("⚠️ Select dates");
            return;
        }

        if (new Date(checkIn) >= new Date(checkOut)) {
            alert("❌ Invalid date");
            return;
        }

        try {
            await API.post("/bookings", {
                userId: Number(userId), // ✅ FIX
                roomId: roomId,
                checkIn,
                checkOut
            });

            alert("✅ Booking successful");
        } catch (err) {
            alert(err.response?.data || "❌ Booking failed");
        }
    };

    return (
        <div style={{ padding: "20px" }}>
            <h2>Rooms</h2>

            <h3>Select Dates</h3>

            <input
                type="datetime-local"
                value={checkIn}
                onChange={(e) => setCheckIn(e.target.value)}
            />

            <input
                type="datetime-local"
                value={checkOut}
                onChange={(e) => setCheckOut(e.target.value)}
                style={{ marginLeft: "10px" }}
            />

            {loading && <p>Loading...</p>}

            {rooms.map((r) => (
                <div key={r.id}>
                    <b>{r.type}</b> - ₹{r.price}
                    <button onClick={() => bookRoom(r.id)}>Book</button>
                </div>
            ))}
        </div>
    );
}

export default HotelDetails;