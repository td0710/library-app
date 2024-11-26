import React from "react";
import { Navbar } from "./layouts/NavbarAndFooter/Navbar";
import { ExploreTopBooks } from "./layouts/HomePage/ExploreTopBooks";
import { Carousel } from "./layouts/HomePage/Carousel";
import "./App.css";

function App() {
  return (
    <div>
      <Navbar />
      <ExploreTopBooks />
      <Carousel />
    </div>
  );
}

export default App;
