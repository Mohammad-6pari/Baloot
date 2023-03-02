package Baloot.Presentation;

import Baloot.Business.Command;

public interface IView {
    Command getInput();
    void output(Response response);
}
