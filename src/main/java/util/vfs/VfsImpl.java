package util.vfs;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class VfsImpl implements Vfs {
    private String dirName;

    public VfsImpl(String dirName) {
        this.dirName = dirName;
    }

    public Iterator<String> getIterator(String directory) {
        return new FileIterator(directory);
    }

    private class FileIterator implements Iterator<String> {
        private Queue<File> files = new LinkedList<>();

        public FileIterator(String directory) {
            Collections.addAll(files, (new File(directory)).listFiles());
        }

        @Override
        public boolean hasNext() {
            return !files.isEmpty();
        }

        @Override
        public String next() {
            File file = files.peek();

            if (file.isDirectory()) {
                Collections.addAll(files, file.listFiles());
            }

            return files.poll().getPath();
        }
    }
}
