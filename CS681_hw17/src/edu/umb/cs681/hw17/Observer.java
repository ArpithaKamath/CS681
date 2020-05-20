package edu.umb.cs681.hw17;

@FunctionalInterface
public interface Observer {
        public abstract void update(Observable o, Object arg);

}
