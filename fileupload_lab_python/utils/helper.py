import os
import shutil

def rename_file(src: str, dest: str) -> None:
    dest_dir = os.path.dirname(dest)
    os.makedirs(dest_dir, exist_ok=True)
    shutil.move(src, dest)

