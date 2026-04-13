import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import Login from "./pages/Login";
import HotelDetails from "./pages/HotelDetails";
import BookingHistory from "./pages/BookingHistory";

function App() {
    return (
        <Router>
            <Navbar />

            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/hotel/:id" element={<HotelDetails />} />
                <Route path="/bookings" element={<BookingHistory />} />
            </Routes>
        </Router>
    );
}

export default App;