import React from "react";
import { Navbar } from "./layouts/NavbarAndFooter/Navbar";
import { ExploreTopBooks } from "./layouts/HomePage/components/ExploreTopBooks";
import { Carousel } from "./layouts/HomePage/components/Carousel";
import "./App.css";
import { Heros } from "./layouts/HomePage/components/Heros";
import { Footer } from "./layouts/NavbarAndFooter/Footer";
import { HomePage } from "./layouts/HomePage/HomePage";
import { SearchBooksPage } from "./layouts/NewBooksPage/NewBooksPage";
export const App = () => {
  return (
    <div>
      <Navbar />
      <SearchBooksPage></SearchBooksPage>
      <Footer></Footer>
    </div>
  );
};
