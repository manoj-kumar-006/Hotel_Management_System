import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";
import HotelCard from "../components/HotelCard";

function Home() {
    const [location, setLocation] = useState("");
    const [hotels, setHotels] = useState([]);

    const navigate = useNavigate();

    // ✅ Protect route (must login)
    useEffect(() => {
        const token = localStorage.getItem("token");

        if (!token) {
            navigate("/login");
        }
    }, []);

    const searchHotels = async () => {
        try {
            const res = await API.get(`/hotels/search?location=${location}`);
            setHotels(res.data);
        } catch (err) {
            alert("No hotels found");
        }
    };

    return (
        <div style={{ padding: "20px" }}>
            <h1>Search Hotels</h1>

            <input
                placeholder="Enter location"
                onChange={(e) => setLocation(e.target.value)}
            />

            <button onClick={searchHotels}>Search</button>

            <div>
                {hotels.map((h) => (
                    <HotelCard key={h.id} hotel={h} />
                ))}
            </div>
        </div>
    );
}

export default Home;