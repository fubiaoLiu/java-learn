package com.xiaoliu.learn.framework.command;

/**
 * @description: 使用命令模式
 * 不同的请求封装到不同的Command类中，然后交给同一个命令执行器执行即可。
 * @author: liufb
 * @create: 2020/5/17 20:44
 **/
public class CommandPattern {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        invoker.setCommand(new CommandA());
        invoker.executor();

        invoker.setCommand(new CommandB());
        invoker.executor();
    }

    public interface Command {
        void executor();
    }

    public static class CommandA implements Command {
        @Override
        public void executor() {
            System.out.println("执行命令A的功能逻辑");
        }
    }

    public static class CommandB implements Command {
        @Override
        public void executor() {
            System.out.println("执行命令B的功能逻辑");
        }
    }

    public static class Invoker {
        private Command command;

        public Command getCommand() {
            return command;
        }

        public void setCommand(Command command) {
            this.command = command;
        }

        public void executor() {
            System.out.println("执行其他前置逻辑");
            command.executor();
            System.out.println("执行其他后置逻辑");
        }
    }
}
