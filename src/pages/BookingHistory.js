import { useEffect, useState } from "react";
import API from "../services/api";

function BookingHistory() {
    const [data, setData] = useState([]);

    useEffect(() => {
        const userId = localStorage.getItem("userId");

        if (!userId) return;

        API.get(`/bookings/user/${userId}`)
            .then(res => setData(res.data));
    }, []);

    return (
        <div style={{ padding: "20px" }}>
            <h2>Bookings</h2>

            {data.map((b) => (
                <div key={b.id}>
                    {b.status} | {b.checkIn} → {b.checkOut}
                </div>
            ))}
        </div>
    );
}

export default BookingHistory;