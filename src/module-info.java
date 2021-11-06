module TheLifeSaver {
	opens application;
	opens application.model;
	opens application.controller;
	opens application.view;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
}